# Modern JDBC Setup (JDBC 4.0+) -- Detailed

## 1. Key Improvement: No Driver Loading

### Why?

-   Uses SPI (Service Provider Interface)

Driver auto-loaded from: META-INF/services/java.sql.Driver

------------------------------------------------------------------------

## 2. Connection Setup

``` java
Connection conn = DriverManager.getConnection(
    "jdbc:mysql://localhost:3306/jdbcLearning?allowPublicKeyRetrieval=true&useSSL=false",
    "root",
    "password"
);
```

------------------------------------------------------------------------

## 3. Try-With-Resources

``` java
try(Connection conn = ...;
    PreparedStatement ps = conn.prepareStatement(query)) {
}
```

### Why?

-   Automatically closes resources
-   Prevents leaks

------------------------------------------------------------------------

## 4. Query Execution

``` java
PreparedStatement ps = conn.prepareStatement(query);
ResultSet rs = ps.executeQuery();
```

------------------------------------------------------------------------

## 5. Row Mapping

``` java
User user = new User(
    rs.getInt("id"),
    rs.getString("name")
);
```

------------------------------------------------------------------------

## 6. Exception Handling

``` java
catch(SQLException e)
```

### Why?

-   Provides SQL state and error codes

------------------------------------------------------------------------

## Summary

Modern JDBC: - Cleaner - Safer - Less boilerplate
