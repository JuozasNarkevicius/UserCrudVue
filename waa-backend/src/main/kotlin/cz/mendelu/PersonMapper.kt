package cz.mendelu

import org.apache.ibatis.annotations.Delete
import org.apache.ibatis.annotations.Insert
import org.apache.ibatis.annotations.Select
import org.apache.ibatis.annotations.Update

interface PersonMapper {

    @Select(
        "SELECT id_person, nickname, first_name, last_name, birth_day, height, gender",
        "FROM Person ORDER BY id_person")
    fun findAll():List<Person>

    @Select(
        "SELECT id_person, nickname, first_name, last_name, birth_day, height, gender",
        "FROM Person WHERE id_person=#{value}")
    fun findById(id:Int):Person

    //@Insert(
    //    "INSERT INTO Person (nickname, first_name, last_name, height) VALUES(#{nickname}, #{firstname}, #{lastname}, #{height})"
    //)
    //fun save(person:Person):Int?

    @Insert(
        "INSERT INTO Person (nickname, first_name, last_name, height, birth_day, gender) VALUES(#{nickname}, #{firstname}, #{lastname}, #{height}, #{birthday}, #{gender})"
    )
    fun save(person:Person):Int?

    @Delete(
        "DELETE FROM Person WHERE id_person=#{value}")
    fun delete(id:Int):Int?

    @Update(
        "UPDATE Person SET nickname=#{nickname}, first_name=#{firstname}, last_name=#{lastname}, height=#{height}, birth_day=#{birthday}, gender=#{gender} WHERE id_person=#{idperson}"
    )
    fun update(person:Person):Int?
}