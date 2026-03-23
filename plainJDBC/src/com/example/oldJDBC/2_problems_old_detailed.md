# Problems in Old JDBC (Detailed)

## 1. Driver Loading Issue

``` java
Class.forName(...)
```

-   Repeated calls
-   Tight coupling to driver class

------------------------------------------------------------------------

## 2. Resource Leak Risk

``` java
finally {
    conn.close();
}
```

### Problem:

-   If developer forgets → connection leak
-   Leads to DB crash under load

------------------------------------------------------------------------

## 3. Boilerplate Code Explosion

Every method requires: - Connection creation - Statement creation -
Cleanup

------------------------------------------------------------------------

## 4. Poor Exception Handling

``` java
catch(Exception e)
```

### Problem:

-   No SQL error codes
-   Hard debugging

------------------------------------------------------------------------

## 5. No Abstraction

-   Business logic + DB logic mixed
-   Violates separation of concerns

------------------------------------------------------------------------

## 6. No Connection Pooling

-   Each call creates new connection
-   Expensive operation

------------------------------------------------------------------------

## Real Impact

-   Poor scalability
-   Hard maintenance
-   Bug-prone system
