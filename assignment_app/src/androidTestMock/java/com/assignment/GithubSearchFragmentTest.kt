package com.assignment


import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.ViewAssertion
import android.support.test.espresso.action.ViewActions
import android.support.test.espresso.action.ViewActions.closeSoftKeyboard
import android.support.test.espresso.action.ViewActions.replaceText
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.contrib.RecyclerViewActions
import android.support.test.espresso.matcher.ViewMatchers.*
import android.support.test.filters.LargeTest
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import com.assignment.search.view.adapter.UserListAdapter
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.Matchers.*
import org.hamcrest.TypeSafeMatcher
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@LargeTest
@RunWith(AndroidJUnit4::class)
class GithubSearchFragmentTest {

    @Rule
    @JvmField
    var mActivityTestRule = ActivityTestRule(GithubSearchMainActivity::class.java)

    @Test
    fun searchAnything_givesReposInRecyclerView() {
        val appCompatEditText = onView(allOf(withId(R.id.searchEditText),
                childAtPosition(allOf(withId(R.id.mvvm_container),
                        childAtPosition(
                                withClassName(`is`("android.support.v7.widget.CardView")),
                                0)), 2), isDisplayed()))
        appCompatEditText.perform(replaceText("stefan"), closeSoftKeyboard())

        onView(allOf(withId(R.id.userList), isDisplayed())).check(hasItems())
    }

    private fun childAtPosition(
            parentMatcher: Matcher<View>, position: Int): Matcher<View> {

        return object : TypeSafeMatcher<View>() {
            override fun describeTo(description: Description) {
                description.appendText("Child at position $position in parent ")
                parentMatcher.describeTo(description)
            }

            public override fun matchesSafely(view: View): Boolean {
                val parent = view.parent
                return parent is ViewGroup && parentMatcher.matches(parent)
                        && view == parent.getChildAt(position)
            }
        }
    }

    fun hasItems(): ViewAssertion {
        return ViewAssertion { view,
                               e ->
            if (view !is RecyclerView) {
                throw e
            }
            assertThat(view.adapter!!.itemCount, greaterThan(0))
        }
    }

    @Test
    fun clickOnRecyclerView_ShowsUserInfoFragment() {
        val appCompatEditText = onView(
                allOf(withId(R.id.searchEditText),
                        childAtPosition(allOf(withId(R.id.mvvm_container),
                                childAtPosition(withClassName(`is`("android.support.v7.widget.CardView")),
                                        0)), 2), isDisplayed()))
        appCompatEditText.perform(replaceText("stefan"), closeSoftKeyboard())


        onView(withId(R.id.userList)).perform(RecyclerViewActions
                .actionOnItemAtPosition<UserListAdapter.ViewHolder>(0, ViewActions.click()))
        onView(allOf(withId(R.id.followersNumber), isDisplayed())).check(matches(withText(containsString("1"))))
        onView(allOf(withId(R.id.userNameF), isDisplayed())).check(matches(withText(containsString("stefanmiodrag"))))
    }
}
