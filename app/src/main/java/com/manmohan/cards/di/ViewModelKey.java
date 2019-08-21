package com.manmohan.cards.di;

import dagger.MapKey;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import javax.inject.Scope;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Scope
@Documented
@MapKey
@Target(METHOD)
@Retention(RUNTIME)
public @interface ViewModelKey {
  Class value();
}
