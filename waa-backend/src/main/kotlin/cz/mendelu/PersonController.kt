package cz.mendelu

import io.micronaut.http.HttpResponse
import io.micronaut.http.MediaType
import io.micronaut.http.annotation.*
import io.micronaut.validation.Validated

@Validated
@Controller("/persons")
class PersonController {

    @Get("/")
    @Produces(MediaType.TEXT_JSON)
    fun index():List<Person>{
        DatabaseConnector.sqlSessionFactory.openSession().use{session ->
            val mapper = session.getMapper(PersonMapper::class.java)
            return mapper.findAll()
        }
    }

    @Get("/{id}")
    @Produces(MediaType.TEXT_JSON)
    fun getPersonById(@PathVariable id:Int):Person {
        DatabaseConnector.sqlSessionFactory.openSession().use{session ->
            val mapper = session.getMapper(PersonMapper::class.java)
            return mapper.findById(id)
        }
    }

    @Post()
    fun createPerson(@Body person:Person):HttpResponse<*>{
        DatabaseConnector.sqlSessionFactory.openSession().use{session ->
            val mapper = session.getMapper(PersonMapper::class.java)
            mapper.save(person)
            session.commit()
        }

        return HttpResponse.ok<Any>()
    }

    @Delete("/{id}")
    fun deletePerson(@PathVariable id:Int):HttpResponse<*>{
        DatabaseConnector.sqlSessionFactory.openSession().use{session ->
            val mapper = session.getMapper(PersonMapper::class.java)
            mapper.delete(id)
            session.commit()
        }

        return HttpResponse.ok<Any>()
    }

    @Put
    fun updatePerson(@Body person:Person):HttpResponse<*>{
        DatabaseConnector.sqlSessionFactory.openSession().use{session ->
            val mapper = session.getMapper(PersonMapper::class.java)
            mapper.update(person)
            session.commit()
        }
        return HttpResponse.ok<Any>()
    }
}