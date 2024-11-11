package seg3x02.employeeGql.resolvers.types

class CreateEmployeeInput(
        val first_name: String? = null,
        val last_name: String? = null,
        val email: String? = null,
        val birthDate: String? = null,
        val address: String? = null,
        val salary: Float? = null,
)
