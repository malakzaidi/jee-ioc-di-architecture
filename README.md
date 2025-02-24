### **JEE Dependency Injection & Inversion of Control**  
🚀 **Mastering IoC & DI in Java EE Architecture**  

This repository explores **Inversion of Control (IoC)** and **Dependency Injection (DI)** in **Java EE** applications. It covers fundamental concepts, best practices, and real-world examples to help developers design loosely coupled, scalable, and maintainable applications.  

### **What You'll Find in This Repository:**  
✅ **Concepts & Principles:** Understanding IoC and DI in JEE  
✅ **Code Examples:** Practical implementations using CDI (Contexts and Dependency Injection), Spring, and Jakarta EE  
✅ **Best Practices:** How to design maintainable and testable code  
✅ **Common Pitfalls:** Mistakes to avoid when implementing DI  

🔗 **Perfect for:** JEE developers, architects, and students looking to deepen their understanding of modern software design patterns in enterprise applications.  

Below is a detailed and professional `README.md` file for your project. It explains the steps, concepts, and implementation details for creating the interfaces, their implementations, and dependency injection using static, dynamic, and Spring Framework approaches.

---

# JEE IOC/DI Architecture Example

This project demonstrates the implementation of a simple Java application using Inversion of Control (IoC) and Dependency Injection (DI) principles. It includes the creation of interfaces, their implementations, and different ways to inject dependencies.

## Table of Contents
1. [Project Overview](#project-overview)
2. [Step 1: Create the `IDao` Interface](#step-1-create-the-idao-interface)
3. [Step 2: Implement the `IDao` Interface](#step-2-implement-the-idao-interface)
4. [Step 3: Create the `IMetier` Interface](#step-3-create-the-imetier-interface)
5. [Step 4: Implement the `IMetier` Interface with Loose Coupling](#step-4-implement-the-imetier-interface-with-loose-coupling)
6. [Step 5: Dependency Injection](#step-5-dependency-injection)
   - [a. Static Instantiation](#a-static-instantiation)
   - [b. Dynamic Instantiation](#b-dynamic-instantiation)
   - [c. Using Spring Framework](#c-using-spring-framework)
     - [XML Configuration](#xml-configuration)
     - [Annotations Configuration](#annotations-configuration)
7. [How to Run the Project](#how-to-run-the-project)
8. [Conclusion](#conclusion)

---

## Project Overview

This project illustrates the use of Inversion of Control (IoC) and Dependency Injection (DI) in Java. It involves:
- Creating interfaces (`IDao` and `IMetier`).
- Implementing these interfaces.
- Injecting dependencies using different methods: static instantiation, dynamic instantiation, and Spring Framework (both XML and annotations).

---

## Step 1: Create the `IDao` Interface

The `IDao` interface defines a method `getData()` that retrieves data from a data source (e.g., a database, file, or API).

```java
public interface IDao {
    double getData();
}
```

---

## Step 2: Implement the `IDao` Interface

We create a concrete implementation of the `IDao` interface. For simplicity, this implementation returns a hardcoded value.

```java
public class DaoImpl implements IDao {
    @Override
    public double getData() {
        // Simulate fetching data from a database
        return 10.0;
    }
}
```

---

## Step 3: Create the `IMetier` Interface

The `IMetier` interface defines a method `calcul()` that performs some business logic using data provided by the `IDao` interface.

```java
public interface IMetier {
    double calcul();
}
```

---

## Step 4: Implement the `IMetier` Interface with Loose Coupling

We implement the `IMetier` interface using loose coupling. This means the `MetierImpl` class depends on the `IDao` interface, not a specific implementation.

```java
public class MetierImpl implements IMetier {
    private IDao dao;

    // Constructor for dependency injection
    public MetierImpl(IDao dao) {
        this.dao = dao;
    }

    @Override
    public double calcul() {
        double data = dao.getData();
        return data * 2; // Example business logic
    }
}
```

---

## Step 5: Dependency Injection

Dependency Injection (DI) is a design pattern that allows objects to receive their dependencies from an external source rather than creating them internally. Below are three ways to achieve DI in this project.

### a. Static Instantiation

In this approach, dependencies are instantiated manually in the code.

```java
public class StaticInjection {
    public static void main(String[] args) {
        IDao dao = new DaoImpl(); // Instantiate dependency
        IMetier metier = new MetierImpl(dao); // Inject dependency
        System.out.println("Result: " + metier.calcul());
    }
}
```

### b. Dynamic Instantiation

In this approach, dependencies are instantiated dynamically at runtime using a configuration file or user input.

```java
public class DynamicInjection {
    public static void main(String[] args) throws Exception {
        // Load class name from a configuration file or user input
        String daoClassName = "com.example.DaoImpl";
        Class<?> daoClass = Class.forName(daoClassName);
        IDao dao = (IDao) daoClass.getDeclaredConstructor().newInstance();

        IMetier metier = new MetierImpl(dao); // Inject dependency
        System.out.println("Result: " + metier.calcul());
    }
}
```

### c. Using Spring Framework

The Spring Framework provides built-in support for dependency injection. Below are two ways to configure Spring: XML and annotations.

#### XML Configuration

1. Add Spring dependencies to your `pom.xml` (for Maven) or `build.gradle` (for Gradle).
2. Create a Spring configuration file (`applicationContext.xml`):

```xml
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
           http://www.springframework.org/schema/beans/spring-beans.xsd">

    <bean id="dao" class="com.example.DaoImpl" />
    <bean id="metier" class="com.example.MetierImpl">
        <constructor-arg ref="dao" />
    </bean>
</beans>
```

3. Use the Spring context to inject dependencies:

```java
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringXmlInjection {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        IMetier metier = (IMetier) context.getBean("metier");
        System.out.println("Result: " + metier.calcul());
    }
}
```

#### Annotations Configuration

1. Enable component scanning in the Spring configuration file (`applicationContext.xml`):

```xml
<context:component-scan base-package="com.example" />
```

2. Annotate the classes with `@Component` and `@Autowired`:

```java
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;

@Component
public class DaoImpl implements IDao {
    @Override
    public double getData() {
        return 10.0;
    }
}

@Component
public class MetierImpl implements IMetier {
    private IDao dao;

    @Autowired
    public MetierImpl(IDao dao) {
        this.dao = dao;
    }

    @Override
    public double calcul() {
        double data = dao.getData();
        return data * 2;
    }
}
```

3. Use the Spring context to inject dependencies:

```java
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringAnnotationsInjection {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        IMetier metier = (IMetier) context.getBean("metierImpl");
        System.out.println("Result: " + metier.calcul());
    }
}
```

---

## How to Run the Project

1. Clone the repository:
   ```bash
   git clone https://github.com/malakzaidi/jee-ioc-di-architecture.git
   ```
2. Navigate to the project directory:
   ```bash
   cd jee-ioc-di-architecture
   ```
3. Compile and run the desired class (e.g., `StaticInjection`, `DynamicInjection`, `SpringXmlInjection`, or `SpringAnnotationsInjection`).

---

## Conclusion

This project demonstrates the principles of Inversion of Control (IoC) and Dependency Injection (DI) in Java. It shows how to create loosely coupled components and inject dependencies using static instantiation, dynamic instantiation, and the Spring Framework (both XML and annotations). These techniques are essential for building scalable and maintainable applications.
