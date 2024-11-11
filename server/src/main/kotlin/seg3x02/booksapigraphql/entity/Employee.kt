package seg3x02.employeeGql.entity

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document(collection = "employee")
data class Employee(
        var first_name: String,
        var last_name: String,
        var email: String,
        var birthDate: String,
        var address: String,
        var salary: Float,
) {
    @Id var id: String = ""
}
