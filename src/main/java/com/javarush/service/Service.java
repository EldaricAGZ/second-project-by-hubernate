package com.javarush.service;

import com.javarush.dao.BaseRepository;
import com.javarush.dao.InventoryRepository;
import com.javarush.dao.RentalRepository;
import com.javarush.dao.factory.RepositoryFactory;
import com.javarush.dto.AddressDto;
import com.javarush.dto.CustomerDto;
import com.javarush.dto.FilmDto;
import com.javarush.dto.mapper.AddressMapper;
import com.javarush.dto.mapper.CustomerMapper;
import com.javarush.dto.mapper.FilmMapper;
import com.javarush.entity.*;
import com.javarush.exception.UserNotFoundException;
import com.javarush.util.SessionManager;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Random;

public class Service {

    private static final Random RANDOM = new Random();
    private static Logger logger = LoggerFactory.getLogger(Service.class);

    private BaseRepository<Customer> customerRepository;
    private BaseRepository<Address> addressRepository;
    private BaseRepository<Store> storeRepository;
    private RentalRepository rentalRepository;
    private InventoryRepository inventoryRepository;
    private BaseRepository<Payment> paymentRepository;

    private CustomerMapper customerMapper;
    private AddressMapper addressMapper;
    private FilmMapper filmMapper;

    public Service() {
        customerRepository = RepositoryFactory.getRepository(Customer.class);
        addressRepository = RepositoryFactory.getRepository(Address.class);
        storeRepository = RepositoryFactory.getRepository(Store.class);
        rentalRepository = (RentalRepository) RepositoryFactory.getRepository(Rental.class);
        inventoryRepository = (InventoryRepository) RepositoryFactory.getRepository(Inventory.class);
        paymentRepository = RepositoryFactory.getRepository(Payment.class);

        customerMapper = CustomerMapper.INSTANCE;
        addressMapper = AddressMapper.INSTANCE;
        filmMapper = FilmMapper.INSTANCE;
    }

    public boolean createCustomer(CustomerDto customerDto, AddressDto addressDto) {
        Transaction transaction = null;
        try(Session session = SessionManager.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            Address address = addressMapper.toEntity(addressDto);
            Customer customer = customerMapper.toEntity(customerDto);
            Store randomStore = storeRepository.findById(RANDOM.nextInt(2) + 1, session);

            customer.setAddress(address);
            customer.setStore(randomStore);
            customerRepository.create(customer, session);

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            logger.error(e.getMessage());
            return false;
        }
        return true;
    }

    public boolean eventCustomerBackRantFilm(Short customerId, Short filmId) {
        Transaction transaction = null;
        try(Session session = SessionManager.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            Rental rental = rentalRepository.searchByCustomerAndFilm(customerId, filmId, session);

            if (rental == null)
                throw new UserNotFoundException("Rental: " + Rental.class + " not found!");

            rental.setReturnDate(LocalDateTime.now());
            rental.setLastUpdate(LocalDateTime.now());
            rentalRepository.update(rental, session);

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            logger.error(e.getMessage());
            return false;
        }
        return true;
    }

    public boolean eventCustomerComeOnStoreAndRentInventory(Short customerId) {
        Transaction transaction = null;
        try(Session session = SessionManager.getSessionFactory().openSession()) {

            transaction = session.beginTransaction();

            Inventory freeFilm = inventoryRepository.tryIsFreeFilm(session);
            if (freeFilm == null) throw new UserNotFoundException("No free films available for rent!");

            Customer customer = customerRepository.findById(customerId, session);
            if (customer == null) throw new UserNotFoundException("Customer with id: "
                        + customerId
                        + " not found.");

            Rental rental = new Rental(LocalDateTime.now(),
                    freeFilm,
                    customer,
                    null,
                    freeFilm.getStore().getManager(),
                    null);

            rentalRepository.create(rental, session);

            Payment payment = new Payment(customer,
                    freeFilm.getStore().getManager(),
                    rental,
                    BigDecimal.valueOf(RANDOM.nextInt(100)),
                    LocalDateTime.now(),
                    null);
            paymentRepository.create(payment, session);

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            logger.error(e.getMessage());
            return false;
        }
        return true;
    }

    public boolean filmWasMadeAndBecameAvailableForRent(FilmDto filmDto, Short storeId) {
        Transaction transaction = null;
        try (Session session = SessionManager.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            Store store = storeRepository.findById(storeId, session);
            if (store == null) throw new UserNotFoundException("Store with id: " + storeId + " don't exist.");

            Film film = FilmMapper.INSTANCE.toEntity(filmDto);
            inventoryRepository.create(new Inventory(null, film, store, null), session);

            transaction.commit();
        } catch(Exception e) {
            if (transaction != null) transaction.rollback();
            logger.error(e.getMessage());
            return false;
        }
        return true;
    }
}
