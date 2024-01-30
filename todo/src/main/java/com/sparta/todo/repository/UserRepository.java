package com.sparta.todo.repository;

import com.sparta.todo.entity.Todo;
import com.sparta.todo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUserName(String username);

    @Query("select u.todos from User u")
    List<List<Todo>> findAllTodosByUsers();
}
