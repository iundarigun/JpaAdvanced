# Attribute Converter

This repo is a sample to apply attibute converter

## Docker postgres

```shell
docker run --rm --name local-postgres -p 5432:5432 -e POSTGRES_USER=attribute_converter -e POSTGRES_PASSWORD=attribute_converter -d postgres
```

---
## Reference
- https://thorben-janssen.com/jpa-attribute-converter/