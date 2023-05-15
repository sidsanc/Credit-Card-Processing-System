## 🔒 Credit Card Processing System

This is a credit card processing system that validates and processes credit card data. It includes features for reading credit card data from different file formats, such as CSV, JSON, and XML, and writing the processed data to an output file.

📄 [Google Document Link](https://docs.google.com/document/d/15GaRNP1hhHcpWiPKKO7_HDL4mmcI5g2ERHqV3PRHm2o/)

### 🎯 Problem Statement:

#### 🔹 Primary Problem:
The credit card processing system must be able to read credit card data from different file formats, such as CSV, JSON, and XML.
The primary problem this system aims to solve is the validation and processing of credit card data. It provides a streamlined solution for reading credit card information from various file formats, validating the card numbers, and determining the card type. Additionally, it generates output data containing the card number and card type for further processing or storage.

#### 🔹 Secondary Problems:

The secondary problems addressed by this system include:

1️⃣ <ins>Handling different file formats</ins>: 
The system allows reading credit card data from CSV, JSON, and XML files, making it flexible and adaptable to different data sources.

2️⃣ <ins>Validating card numbers</ins>: 
The system verifies the card numbers for various conditions, such as length, presence of alphabetic characters, and adherence to specific patterns for different card types.

3️⃣ <ins>Determining card type</ins>: 
Based on the card number, the system identifies the card type, such as Visa, MasterCard, American Express, or Discover.

### 🎨 DESIGN PATTERN: FACTORY DESIGN PATTERN and STRATEGY DESIGN PATTERN.

A design pattern is a formal way of documenting a solution to a design problem. Design patterns promote reusability that leads to more robust and highly maintainable code. Java Design Patterns are divided into three categories:

1️⃣ Creational

2️⃣ Structural

3️⃣ Behavioral

#### 💡 FACTORY PATTERN is <ins>Creational Design pattern</ins>.

It is used when we have a parent class with multiple child classes and based on input, we need to return one of the child-class.
This pattern takes out the responsibility of the instantiation of a class from the client program to the factory class.

The system employs the Factory Method pattern in the CreditCardFactory class. This pattern encapsulates the logic for creating different types of credit cards (VisaCC, MasterCC, AmExCC, DiscoverCC, InvalidCC) based on the input data (CreditCardEntry). The factory method, createCreditCard, dynamically determines the appropriate credit card object to instantiate.

#### 💻 Factory Class: 
Here is the basic implementation of Factory Class.
```
public class CreditCardFactory {
    public static CreditCardEntry createCreditCard(CreditCardEntry creditCardRecord) {
        String cardNumber = creditCardRecord.getCardNumber();
        String expirationDate = creditCardRecord.getExpirationDate();
        String cardHolderName = creditCardRecord.getCardHolderName();
        if(isNullOrEmpty(cardNumber))
            return new InvalidCC("Invalid: empty/null card number", cardNumber, expirationDate, cardHolderName);
        else if(exceeds19Digit(cardNumber))
            return new InvalidCC("Invalid: more than 19 digits", cardNumber, expirationDate, cardHolderName);
        else if(hasAplhabets(cardNumber))
            return new InvalidCC("Invalid: non numeric characters", cardNumber, expirationDate, cardHolderName);   
        else if (isVisaCC(cardNumber)) 
            return new VisaCC("Visa", cardNumber, expirationDate, cardHolderName);
        else if (isMasterCC(cardNumber))  
        ...
       }
}
```

#### 🎯 STRATEGY PATTERN is a <ins>behavioral design pattern</ins> 
It turns a set of behaviors into objects and makes them interchangeable inside original context object. The original object, called context, holds a reference to a strategy object. The context delegates executing the behavior to the linked strategy object.

The system utilizes the Strategy pattern for reading and writing credit card data in different file formats. The IReaderStrategy and IWriterStrategy interfaces define the common methods (readFile and writeToFile) to be implemented by specific reader and writer classes. The system can switch between different strategies (CSVReader, JSONReader, XMLReader, CSVWriter, JSONWriter, XMLWriter) based on the file format without affecting the client code.

#### 🔸 Strategy Class: 
Here is the basic implementation of Factory Class.

```
            IReaderStrategy filereader;
            IWriterStrategy filewriter = null;
            if (fileType.equalsIgnoreCase("csv")) {
                filereader = new CSVReader(new File(inputFile));
                filewriter = new CSVWriter();
                creditCardEntries = filereader.readFile(inputFile);
            } else if (fileType.equalsIgnoreCase("json")) {
                filereader = new JSONReader(new File(inputFile));
                filewriter = new JSONWriter();
                creditCardEntries = filereader.readFile(inputFile);
            } else if (fileType.equalsIgnoreCase("xml")) {
                System.out.println(" in xml ");
                filereader = new XMLReader(new File(inputFile));
                filewriter = new XMLWriter();
                creditCardEntries = filereader.readFile(inputFile);
            }
The Credit Card Processing System utilizes the following design patterns:
```
### 🔹 Consequences of Using Design Patterns

The use of design patterns in the Credit Card Processing System provides several benefits:

1️⃣ Modularity and extensibility: The system's design is modular, allowing for easy addition of new credit card types and file formats by extending the respective classes or implementing new strategies.

2️⃣ Code reusability: The design patterns promote code reusability by encapsulating common behaviors and separating them from specific implementations. This allows for easier maintenance and reduces code duplication.

3️⃣ Flexibility and adaptability: The system can accommodate changes in credit card validation rules, card types, and file formats by modifying or adding specific classes without affecting the overall system.

🚧 However, there are also some considerations when using design patterns:

🔸 Increased complexity: Implementing design patterns may introduce additional complexity to the system, especially for developers who are not familiar with the patterns. It requires a good understanding of the patterns and their proper application.

🔸 Potential performance impact: Some design patterns introduce indirection or additional layers of abstraction, which may have a slight impact on performance. However, in most cases, the benefits of modularity and maintainability outweigh the performance impact.

Overall, the use of design patterns in the Credit Card Processing System promotes a modular, extensible, and maintainable codebase, enabling efficient processing and validation of credit card data.

![UML Class Diagram](https://github.com/gopinathsjsu/individual-project-sidsanc/blob/main/UML-Diagrams/creditCardE2E_UML.png)









