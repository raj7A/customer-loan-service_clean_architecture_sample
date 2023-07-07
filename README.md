
# Customer Loan service :
Consider below is the business use case shared by a business team on which this sample application is built :
1. Receive request to create a new Loan (car/housing)
2. Check if customer is fraud or not
3. if fraud, don't create a new loan, respond with error
4. if not fraud, create a new Loan
5. Save the loan to DataStore
6. Respond with Loan information

# Clean Architecture :

![](images/Clean_Architecture.png)

# Package structure reflecting Clean Architecture layers :

![](images/Package_Structure.png)

# What does each layer mean :

1. **_Entities_** - The place where our enterprise business rules resides | Core java - framework independent
2. **_UseCases_** - The place where our application business rules resides, which controls the dance of Entities |  Core java - framework independent
3. **_InterfaceAdapters_** - The place where implementation for interfaces of UseCase, database, http call resides | framework dependent
4. **_Configurations (aka Drivers)_** - The place where all the independent components are stitched together to form an application (web) | framework dependent


    Clear boundaries defined :
    - Entities doesn't know anything about its outer cirlces  (i.e UseCase, InterfaceAdapter, configurations layer).
    - UseCases doesn't know anything about its outer circles  (i.e InterfaceAdapter, configurations layer), but knows about its inner circles (i.e Entities)
    - InterfaceAdapter doesn't know anything about is outer circles (i.e Configurations), but knows about its inner circles (i.e Entities, UseCase, InterfaceAdapter layer)

# Why Clean Architecture :

1. _**Entities & UseCases are central and everything else support  it**_ - which means our application is purely driven by business aspects and not from technical perspective
2. **_Independent of frameworks_**  - easily swap the framework(like springboot, jpa etc) without affecting the Entities & UseCases
3. **_Independent of Database_** - easily swap the database (like sql, nosql etc) without affecting the Entities & UseCases
4. **_Independent of external interactions_** - Changes in the external http contract doesn't affect the Entities & UseCases (unless its required to update the domain with business logics) 

# AppMap :

![](images/Dependency_Map.png)

![](images/appMap.svg)
# Note:
This application is used for demo purpose, hence exhaustive test cases/exception handling etc are not covered in the applications.

# References :
https://www.oreilly.com/library/view/clean-architecture-a/9780134494272/