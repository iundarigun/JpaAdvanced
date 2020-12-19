# Soft Delete 

This repo aims to expore functionalities about soft delete using `@SQLDelete` and `@Where`

### Tricks

- SQLDelete: If you are using other features like `@UpdateTimestamp` or `@Version`, you need to prepare manually the update statement, setting new values update time and version fields, and adding Version as a parameter for the query.
- Where: Works fine where you are selecting items for this entity (in our case, **Employee**). But, relation with **Invoice** we have two problems:
  - If you get an invoice for deleted employee, you will have problems loading employee because JPA doesn't found this employee
  - If you configure `@EntityGraph` for your selects and try to get employees using the same query, `Where` statement will not be applied.

## Reference 

* https://levelup.gitconnected.com/spring-boot-soft-delete-functionality-with-hibernate-f5ee8c24c99f

