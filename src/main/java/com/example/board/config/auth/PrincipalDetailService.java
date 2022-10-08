package com.example.board.config.auth;

import com.example.board.model.User;
import com.example.board.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class PrincipalDetailService implements UserDetailsService {
    //해당 username이 DB에 있는지 확인
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User principal = userRepository.findByUsername(username).orElseThrow(()-> new UsernameNotFoundException("해당 사용자를 찾을 수 없습니다." + username));
        return new PrincipalDetail(principal);//시큐리티 세션에 저장
    }
}
