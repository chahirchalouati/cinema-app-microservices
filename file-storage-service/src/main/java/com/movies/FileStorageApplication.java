package com.movies;

import com.github.javafaker.Faker;
import com.movies.domain.Permission;
import com.movies.domain.Role;
import com.movies.domain.User;
import com.movies.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

@SpringBootApplication
@EnableEurekaClient
public class FileStorageApplication {

    public static void main(String[] args) {
        SpringApplication.run(FileStorageApplication.class, args);
    }

    @Autowired
    private UserRepository userRepository;

    @Bean
    public Faker faker() {
        return new Faker();
    }

    @Bean
    public CommandLineRunner runner() {
        return args -> {
            IntStream.iterate(0, i -> i + 1).limit(100).forEach(value -> {
                final List<Role> roles = new ArrayList<>(0);
                final List<User.Animal> animals = new ArrayList<>(0);
                IntStream.iterate(0, i -> i + 1).limit(10).forEach(value_1 -> {
                    var role = faker().lorem().sentence();
                    List<Permission> permissions = new ArrayList<>(0);
                    IntStream.iterate(0, i -> i + 1).limit(10).forEach(value_3 -> {
                        var permission = new Permission(faker().lorem().sentence());
                        permissions.add(permission);
                    });
                    roles.add(new Role(role, permissions));
                    IntStream.iterate(0, i -> i + 1).limit(10).forEach(value_3 -> {
                        var anima = new User.Animal(faker().animal().name());
                        animals.add(anima);
                    });
                });

                var user = new User();
                user.setFirstname(faker().name().firstName());
                user.setLastname(faker().name().lastName());
                user.setUsername(faker().name().username());
                user.setRoles(roles);
                user.setAnimals(animals);
                user.setDescription(faker().lorem().sentence(10000));
                final User save = userRepository.save(user);


            });
            System.out.println("done.");
        };
    }


}
