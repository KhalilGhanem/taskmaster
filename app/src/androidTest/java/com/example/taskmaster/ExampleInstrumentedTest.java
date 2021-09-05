package com.example.taskmaster;

import android.content.Context;

import androidx.test.espresso.Espresso;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.espresso.contrib.RecyclerViewActions;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static org.junit.Assert.*;

import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@LargeTest
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {

    @Rule
    public ActivityScenarioRule<MainActivity> activityScenarioRule
            = new ActivityScenarioRule<>(MainActivity.class);


    @Test
    public void useAppContext() {
        onView(withId(R.id.textView)).check(matches(isDisplayed()));

//        onView(withId(R.id.textView)).check(matches(isDisplayed()));

        // Context of the app under test.
//        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
//        assertEquals("com.example.taskmaster", appContext.getPackageName());
    }
    @Test
    public void testSetting(){
        onView(withId(R.id.SettingsButton)).perform(click());
        onView(withId(R.id.SettingsUserName)).perform(typeText("khalil"),closeSoftKeyboard());
        onView(withId(R.id.SettingsSaveButton)).perform(click());
        Espresso.pressBackUnconditionally();
        onView(withId(R.id.userTasksView)).check(matches(withText("khalil ’s tasks")));
    }

    @Test
    public void  testRecyclerView(){
        onView(withId(R.id.TasksListRecyclerView)).perform(RecyclerViewActions.actionOnItemAtPosition(0,click()));
        onView(withId(R.id.TaskDetailTitle)).check(matches(withText("DO Labs")));

    }

    @Test
    public void testAddTask(){
        onView(withId(R.id.button)).perform(click());
        onView(withId(R.id.editTextTextPersonName)).perform(typeText("lab31"),closeSoftKeyboard());
        onView(withId(R.id.editTextTextPersonName2)).perform(typeText("Espresso and Polish"),closeSoftKeyboard());
        onView(withId(R.id.addTaskButton)).perform(click());
        onView(withId(R.id.TasksListRecyclerView)).perform(RecyclerViewActions.actionOnItemAtPosition(6,click()));
        onView(withId(R.id.TaskDetailTitle)).check(matches(withText("lab31")));

    }
}