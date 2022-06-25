package br.com.devcave.jpa.converter

import br.com.devcave.jpa.domain.Document
import javax.persistence.AttributeConverter
import javax.persistence.Converter


@Converter(autoApply = true)
class DocumentConverter: AttributeConverter<Document, String> {
    override fun convertToDatabaseColumn(attribute: Document?): String? {
        return attribute?.let { attribute.number + "-" + attribute.letter }
    }

    override fun convertToEntityAttribute(dbData: String?): Document? {
        return dbData?.let {
            Document(it.split("-")[0], it.split("-")[1])
        }
    }
}