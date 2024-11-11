package seg3x02.employeeGql.resolvers

import java.util.*
import org.springframework.data.mongodb.core.MongoOperations
import org.springframework.graphql.data.method.annotation.Argument
import org.springframework.graphql.data.method.annotation.MutationMapping
import org.springframework.graphql.data.method.annotation.QueryMapping
import org.springframework.stereotype.Controller
import seg3x02.employeeGql.entity.Employee
import seg3x02.employeeGql.repository.EmployeesRepository
import seg3x02.employeeGql.resolvers.types.CreateEmployeeInput

@Controller
class EmployeesResolver(
        val mongoOperations: MongoOperations,
        private val employeeRepo: EmployeesRepository
) {

    @QueryMapping
    fun employees(): List<Employee> {
        return employeeRepo.findAll()
    }

    @QueryMapping
    fun employeeById(@Argument id: String): Employee? {
        val employee = employeeRepo.findById(id)
        return employee.orElse(null)
    }

    @MutationMapping
    fun newEmployee(@Argument("createEmployeeInput") input: CreateEmployeeInput): Employee {
        if (input.first_name != null &&
                        input.last_name != null &&
                        input.email != null &&
                        input.birthDate != null &&
                        input.address != null &&
                        input.salary != null
        ) {
            val employee =
                    Employee(
                            input.first_name,
                            input.last_name,
                            input.email,
                            input.birthDate,
                            input.address,
                            input.salary,
                    )
            employee.id = UUID.randomUUID().toString()
            employeeRepo.save(employee)
            return employee
        } else {
            throw Exception("Invalid")
        }
    }

    @MutationMapping
    fun updateEmployee(
            @Argument employeeId: String,
            @Argument("createEmployeeInput") input: CreateEmployeeInput
    ): Employee {
        val employee = employeeRepo.findById(employeeId)
        employee.ifPresent {
            if (input.first_name != null) {
                it.first_name = input.first_name
            }
            if (input.last_name != null) {
                it.last_name = input.last_name
            }
            if (input.email != null) {
                it.email = input.email
            }
            if (input.birthDate != null) {
                it.birthDate = input.birthDate
            }
            if (input.address != null) {
                it.address = input.address
            }
            if (input.salary != null) {
                it.salary = input.salary
            }
            employeeRepo.save(it)
        }
        return employee.get()
    }
}
