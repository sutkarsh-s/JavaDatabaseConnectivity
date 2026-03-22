# Old JDBC Setup (Pre-JDBC 4.0) -- Detailed Guide

## 1. Project Setup

-   Create a Java project in IntelliJ
-   Add MySQL Connector JAR manually via Project Structure

## 2. Why Manual Driver Loading?

Before JDBC 4.0, drivers were NOT auto-registered.

``` java
Class.forName("com.mysql.cj.jdbc.Driver");
```

### Reason:

-   Forces class loading
-   Static block registers driver with DriverManager

------------------------------------------------------------------------

## 3. Establish Connection

``` java
Connection conn = DriverManager.getConnection(
    "jdbc:mysql://localhost:3306/jdbcLearning",
    "root",
    "password"
);
```

### Internals:

-   DriverManager loops through registered drivers
-   Finds matching driver for URL
-   Opens socket connection to DB

------------------------------------------------------------------------

## 4. Execute Query

``` java
String query = "SELECT * FROM users WHERE id = ?";
PreparedStatement ps = conn.prepareStatement(query);
ps.setInt(1, 1);

ResultSet rs = ps.executeQuery();
```

### Why PreparedStatement?

-   Prevents SQL Injection
-   Precompiled → faster

------------------------------------------------------------------------

## 5. Process Result

``` java
while(rs.next()) {
    int id = rs.getInt("id");
    String name = rs.getString("name");
}
```

### Internals:

-   ResultSet is a cursor
-   Moves row by row

------------------------------------------------------------------------

## 6. Manual Resource Cleanup

``` java
finally {
    rs.close();
    ps.close();
    conn.close();
}
```

### Why important?

-   DB connections are expensive
-   Not closing → memory leak + connection exhaustion

------------------------------------------------------------------------

## Summary

Old JDBC is: - Verbose - Error-prone - Hard to maintain
