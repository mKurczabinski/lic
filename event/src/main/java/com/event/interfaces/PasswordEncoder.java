package com.event.interfaces;

public interface PasswordEncoder {
String encode(CharSequence rawPassword);
boolean matches(CharSequence rawPassword, String encodedPassword);

}
