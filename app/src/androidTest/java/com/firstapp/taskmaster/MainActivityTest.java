package com.firstapp.taskmaster;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.core.app.ApplicationProvider.getApplicationContext;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.junit.Assert.*;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class MainActivityTest {
    @Rule
    public ActivityScenarioRule<MainActivity> mainActivityActivityScenarioRule=
            new ActivityScenarioRule<>(MainActivity.class);
    @Test
    public void mainActitvyTitle() {
        onView(withText("Main Page")).check(matches(isDisplayed()));
    }
    @Test
    public void mainActitvyUserName() {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        String result= preferences.getString("name","user unkown");
        onView(withText(result.trim()+"'s Tasks")).check(matches(isDisplayed()));
    }
    @Test
    public void tapDetailsPage(){
       onView(withId(R.id.rvTasks)).perform(click());
       // first task title is one and it perform click on item
        // then check if title is displayed is one
         onView(withText("one")).check(matches(isDisplayed()));
    }
}