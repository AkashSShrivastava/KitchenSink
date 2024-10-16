package com.hitachi.KitchenSink.testController;

import com.hitachi.KitchenSink.model.Member;
import com.hitachi.KitchenSink.service.MemberRegistration;
import com.hitachi.KitchenSink.repository.MemberRepository;
import com.hitachi.KitchenSink.controller.MemberController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class MemberControllerTest {

    @InjectMocks
    private MemberController memberController;

    @Mock
    private MemberRepository memberRepository;

    @Mock
    private MemberRegistration memberRegistration;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testListAllMembers() {
        Member member = new Member("1", "Akash", "sas5198sas@gmail.com", "1234567890");
        when(memberRepository.findAll()).thenReturn(Collections.singletonList(member));

        List<Member> members = memberController.listAllMembers();

        assertEquals(1, members.size());
        assertEquals("Akash", members.get(0).getName());
    }

    @Test
    public void testLookupMemberById() {
        Member member = new Member("1", "Akash", "sas5198sas@gmail.com", "1234567890");
        when(memberRepository.findById("1")).thenReturn(Optional.of(member));

        ResponseEntity<Member> response = (ResponseEntity<Member>) memberController.lookupMemberById("1");

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Akash", response.getBody().getName());
    }

    @Test
    public void testLookupMemberById_NotFound() {
        when(memberRepository.findById("1")).thenReturn(Optional.empty());

        ResponseEntity<Member> response = (ResponseEntity<Member>) memberController.lookupMemberById("1");

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    public void testCreateMember() {
        Member member = new Member("1", "Akash", "sas5198sas@gmail.com", "1234567890");

        ResponseEntity<?> response = memberController.createMember(member);

        verify(memberRegistration, times(1)).register(any(Member.class));
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }
}
