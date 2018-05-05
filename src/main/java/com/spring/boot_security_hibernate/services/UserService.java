package com.spring.boot_security_hibernate.services;

import com.spring.boot_security_hibernate.co.UserCo;
import com.spring.boot_security_hibernate.dao.repository.UserRepository;
import com.spring.boot_security_hibernate.dto.ResponseHandler;
import com.spring.boot_security_hibernate.dto.UserDto;
import com.spring.boot_security_hibernate.entity.Role;
import com.spring.boot_security_hibernate.entity.User;
import com.spring.boot_security_hibernate.enums.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> users = userRepository.findUserByUsername(username);
        users.orElseThrow(() -> new UsernameNotFoundException("User with given name is not present"));
        return users.map(User::new).get();
    }

    public UserDto saveUser(UserCo userCo) {
        User user = new User();
        user.setUsername(userCo.getUsername());
        user.setEmail(userCo.getEmail());
        user.setPassword(userCo.getPassword());
        user.setFirstName(userCo.getFirstName());
        user.setLastName(userCo.getLastName());
        user.setActive(userCo.getActive());
        Set<Role> roleSet = new HashSet<>();
        Role role = new Role(UserRole.USER);
        role.setUser(user);
        roleSet.add(role);
        user.setRoles(roleSet);
        User userResponse = userRepository.save(user);
        return new UserDto().getInstance(userResponse);
    }

    public UserDto getLoggedinUser() {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return new UserDto().getInstance(user);
    }

    public UserDto getUserById(int id) {
        Optional<User> user = userRepository.findById(id);
        user.orElseThrow(() -> new UsernameNotFoundException("User with given id is not present"));
        return new UserDto().getInstance(user.get());
    }

    public List<UserDto> getUsers(Integer page, Integer per_page) {
        int pageNo = (page != null) ? page : 0;
        int per_page_no = (per_page != null) ? per_page : 10;
        PageRequest pageRequest = new PageRequest(pageNo, per_page_no);
        return new UserDto().getInstance(userRepository.findAll(pageRequest));

    }

    public UserDto updateUser(int id, UserCo userCo) {
        Optional<User> user = userRepository.findById(id);
        user.orElseThrow(() -> new UsernameNotFoundException("User with given id is not present"));
        User updateUser = user.get();
        updateUser.setUsername(userCo.getUsername());
        updateUser.setEmail(userCo.getEmail());
        updateUser.setPassword(userCo.getPassword());
        updateUser.setFirstName(userCo.getFirstName());
        updateUser.setLastName(userCo.getLastName());
        updateUser.setActive(userCo.getActive());
        User userResponse = userRepository.save(updateUser);
        return new UserDto().getInstance(userResponse);
    }

    public void deleteUserById(int id) {
        userRepository.deleteById(id);
    }
}
