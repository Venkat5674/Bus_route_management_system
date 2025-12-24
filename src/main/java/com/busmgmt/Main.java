package com.busmgmt;

import com.busmgmt.entity.Bus;
import com.busmgmt.entity.Route;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class Main {
    public static void main(String[] args) {
        // Build SessionFactory
        SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();

        try {
            // 1. Insert 2 Bus records
            insertBuses(factory);

            // 2. Insert 2 Route records
            insertRoutes(factory);

            // 3. Fetch Route by routeId
            fetchRoute(factory, 1);

            // 4. Update Bus ticketPrice
            updateBusPrice(factory, 1, 1500.00);

            // 5. Delete a Route record
            deleteRoute(factory, 2);

            // 6. Add a new Route (Example)
            createRoute(factory, "Mumbai", "Pune", 148.0, 3, true);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            factory.close();
        }
    }

    private static void createRoute(SessionFactory factory, String source, String destination, double distance,
            int duration, boolean active) {
        Session session = factory.openSession();
        Transaction tx = session.beginTransaction();

        System.out.println("\n--- Creating New Route ---");
        Route route = new Route();
        route.setSource(source);
        route.setDestination(destination);
        route.setDistance(distance);
        route.setDuration(duration);
        route.setActive(active);

        session.save(route);

        tx.commit();
        session.close();
        System.out.println("New Route created successfully: " + source + " -> " + destination);
    }

    private static void insertBuses(SessionFactory factory) {
        Session session = factory.openSession();
        Transaction tx = session.beginTransaction();

        System.out.println("--- Inserting Buses ---");

        Bus bus1 = new Bus();
        bus1.setBusNumber("KA-01-F-1234");
        bus1.setBusType("Sleeper");
        bus1.setSource("Bangalore");
        bus1.setDestination("Hyderbad");
        bus1.setTotalSeats(30);
        bus1.setTicketPrice(1200.00);
        bus1.setAvailable(true);

        Bus bus2 = new Bus();
        bus2.setBusNumber("TN-09-X-5678");
        bus2.setBusType("Seater");
        bus2.setSource("Chennai");
        bus2.setDestination("Coimbatore");
        bus2.setTotalSeats(45);
        bus2.setTicketPrice(600.00);
        bus2.setAvailable(true);

        session.save(bus1);
        session.save(bus2);

        tx.commit();
        session.close();
        System.out.println("Buses inserted successfully!");
    }

    private static void insertRoutes(SessionFactory factory) {
        Session session = factory.openSession();
        Transaction tx = session.beginTransaction();

        System.out.println("\n--- Inserting Routes ---");

        Route route1 = new Route();
        route1.setSource("Bangalore");
        route1.setDestination("Hyderbad");
        route1.setDistance(570.5);
        route1.setDuration(9);
        route1.setActive(true);

        Route route2 = new Route();
        route2.setSource("Chennai");
        route2.setDestination("Coimbatore");
        route2.setDistance(505.2);
        route2.setDuration(8);
        route2.setActive(true);

        session.save(route1);
        session.save(route2);

        tx.commit();
        session.close();
        System.out.println("Routes inserted successfully!");
    }

    private static void fetchRoute(SessionFactory factory, int routeId) {
        Session session = factory.openSession();

        System.out.println("\n--- Fetching Route ---");
        Route route = session.get(Route.class, routeId);
        if (route != null) {
            System.out.println("Route Found: " + route.getSource() + " -> " + route.getDestination());
        } else {
            System.out.println("Route not found with ID: " + routeId);
        }

        session.close();
    }

    private static void updateBusPrice(SessionFactory factory, int busId, double newPrice) {
        Session session = factory.openSession();
        Transaction tx = session.beginTransaction();

        System.out.println("\n--- Updating Bus Price ---");
        Bus bus = session.get(Bus.class, busId);
        if (bus != null) {
            System.out.println("Old Price: " + bus.getTicketPrice());
            bus.setTicketPrice(newPrice);
            session.update(bus);
            System.out.println("Price updated successfully! New Price: " + newPrice);
        } else {
            System.out.println("Bus not found with ID: " + busId);
        }

        tx.commit();
        session.close();
    }

    private static void deleteRoute(SessionFactory factory, int routeId) {
        Session session = factory.openSession();
        Transaction tx = session.beginTransaction();

        System.out.println("\n--- Deleting Route ---");
        Route route = session.get(Route.class, routeId);
        if (route != null) {
            session.delete(route);
            System.out.println("Route deleted successfully! ID: " + routeId);
        } else {
            System.out.println("Route not found with ID: " + routeId);
        }

        tx.commit();
        session.close();
    }
}
