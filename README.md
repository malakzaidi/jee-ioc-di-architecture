### **JEE Dependency Injection & Inversion of Control**  
🚀 **Mastering IoC & DI in Java EE Architecture**  

This repository explores **Inversion of Control (IoC)** and **Dependency Injection (DI)** in **Java EE** applications. It covers fundamental concepts, best practices, and real-world examples to help developers design loosely coupled, scalable, and maintainable applications.  

### **What You'll Find in This Repository:**  
✅ **Concepts & Principles:** Understanding IoC and DI in JEE  
✅ **Code Examples:** Practical implementations using CDI (Contexts and Dependency Injection), Spring, and Jakarta EE  
✅ **Best Practices:** How to design maintainable and testable code  
✅ **Common Pitfalls:** Mistakes to avoid when implementing DI  

🔗 **Perfect for:** JEE developers, architects, and students looking to deepen their understanding of modern software design patterns in enterprise applications.  

# IoC and DI Architecture in JEE

![Architecture Banner](https://via.placeholder.com/800x200/0077B6/FFFFFF?text=IoC+and+DI+Architecture+in+JEE)

This project illustrates the application of **Inversion of Control (IoC)** and **Dependency Injection (DI)** principles in a Java EE (JEE) application. The objective is to demonstrate how these concepts can be used to create a modular, maintainable, and testable architecture.

## 📋 Table of Contents

- [📌 Introduction](#introduction)
- [🏗️ Project Structure](#project-structure)
- [💡 Key Concepts](#key-concepts)
  - [Inversion of Control (IoC)](#inversion-of-control-ioc)
  - [Dependency Injection (DI)](#dependency-injection-di)
- [🛠️ Development Steps](#development-steps)
  1. [Creating the `IDao` Interface](#creating-the-idao-interface)
  2. [Implementing `IDao`](#implementing-idao)
  3. [Creating the `IMetier` Interface](#creating-the-imetier-interface)
  4. [Implementing `IMetier` with Loose Coupling](#implementing-imetier-with-loose-coupling)
  5. [Dependency Injection](#dependency-injection)
     - [Using Static Instantiation](#using-static-instantiation)
     - [Using Dynamic Instantiation](#using-dynamic-instantiation)
     - [Using Spring Framework](#using-spring-framework)
       - [XML Version](#xml-version)
       - [Annotations Version](#annotations-version)
- [🏁 Conclusion](#conclusion)

## 📌 Introduction

In modern software development, it's essential to reduce coupling between components to improve code flexibility and maintainability. The principles of Inversion of Control (IoC) and Dependency Injection (DI) are key techniques for achieving this goal. This project demonstrates their application in a JEE application.

## 🏗️ Project Structure

The project is organized into several packages:

| Package | Description |
|---------|-------------|
| `dao` | Contains data access objects |
| `service` | Contains business logic |
| `controller` | Handles HTTP requests and responses |
| `config` | Contains configuration classes and files for IoC and DI |

## 💡 Key Concepts

### Inversion of Control (IoC)

> IoC is a design principle where control over object creation and dependency management is transferred to a container or framework, enabling more flexible and decoupled code.

### Dependency Injection (DI)

> DI is a specific form of IoC where dependencies are provided to a class rather than the class creating them itself. This can be achieved through constructor injection, setter injection, or field injection.

## 🛠️ Development Steps

### 1. Creating the `IDao` Interface

Define an `IDao` interface with a `getData()` method to retrieve data.

```java
public interface IDao {
    Data getData();
}
```

### 2. Implementing `IDao`

Create a `DaoImpl` class that implements the `IDao` interface and provides the concrete logic for retrieving data.

```java
public class DaoImpl implements IDao {
    @Override
    public Data getData() {
        // Logic to retrieve data
        return new Data();
    }
}
```

### 3. Creating the `IMetier` Interface

Define an `IMetier` interface with a `calcul()` method to perform business operations.

```java
public interface IMetier {
    Result calcul();
}
```

### 4. Implementing `IMetier` with Loose Coupling

Create a `MetierImpl` class that implements `IMetier` and uses the `IDao` interface to perform business operations, ensuring loose coupling.

```java
public class MetierImpl implements IMetier {
    private IDao dao;

    public MetierImpl(IDao dao) {
        this.dao = dao;
    }

    @Override
    public Result calcul() {
        Data data = dao.getData();
        // Business logic using the data
        return new Result();
    }
}
```

### 5. Dependency Injection

#### a. Using Static Instantiation

Manually create and provide the necessary dependencies.

```java
public class Application {
    public static void main(String[] args) {
        IDao dao = new DaoImpl();
        IMetier metier = new MetierImpl(dao);
        // Use metier
    }
}
```

#### b. Using Dynamic Instantiation

Use a mechanism to provide dependencies at runtime, for example, using reflection.

```java
public class Application {
    public static void main(String[] args) throws Exception {
        String daoClassName = "com.example.DaoImpl";
        IDao dao = (IDao) Class.forName(daoClassName).getDeclaredConstructor().newInstance();
        IMetier metier = new MetierImpl(dao);
        // Use metier
    }
}
```

#### c. Using Spring Framework

**XML Version**

Configure dependencies in an XML file.

```xml
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
           http://www.springframework.org/schema/beans/spring-beans.xsd">
    <bean id="dao" class="com.example.DaoImpl"/>
    <bean id="metier" class="com.example.MetierImpl">
        <constructor-arg ref="dao"/>
    </bean>
</beans>
```

**Annotations Version**

Use Spring annotations for configuration.

```java
@Configuration
public class AppConfig {
    @Bean
    public IDao dao() {
        return new DaoImpl();
    }
    
    @Bean
    public IMetier metier(IDao dao) {
        return new MetierImpl(dao);
    }
}
```

## 🏁 Conclusion

Implementing IoC and DI principles in JEE applications leads to several benefits:

- **Reduced coupling**: Components depend on abstractions rather than concrete implementations
- **Improved testability**: Dependencies can be easily mocked for unit testing
- **Enhanced maintainability**: Changes to implementations don't affect dependent components
- **Greater flexibility**: Different implementations can be swapped without modifying client code

This architecture provides a solid foundation for building scalable, maintainable JEE applications.

