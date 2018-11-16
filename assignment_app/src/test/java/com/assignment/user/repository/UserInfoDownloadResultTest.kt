package com.assignment.user.repository

import com.assignment.model.users.GithubUser
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock
import org.mockito.MockitoAnnotations

class UserInfoDownloadResultTest {

    lateinit var user: GithubUser

    @Before
    fun setupNotesPresenter() {
        MockitoAnnotations.initMocks(this)
        user = mock(GithubUser::class.java)
        `when`(user.getUserName()).thenReturn("Stefan")
        `when`(user.getRepoName()).thenReturn("")
    }

    @Test
    fun success_givesUserWithSuccessStatus() {
        var userInfoDownloadResult = UserInfoDownloadResult.success(user)

        assertNotNull(userInfoDownloadResult.user)
        assertEquals(UserInfoDownloadResult.ResultStatus.SUCCESS, userInfoDownloadResult.status)
        assertEquals("Stefan", userInfoDownloadResult.user!!.getUserName())
        assertEquals("", userInfoDownloadResult.user!!.getRepoName())
    }

    @Test
    fun failure_givesNullAndFailureStatus() {
        var userInfoDownloadResult = UserInfoDownloadResult.failure()

        assertNull(userInfoDownloadResult.user)
        assertEquals(userInfoDownloadResult.status, UserInfoDownloadResult.ResultStatus.FAILURE)
    }

    @Test
    fun create_givesUserInfoAndSuccessStatus() {
        var userInfoDownloadResult = UserInfoDownloadResult(user, UserInfoDownloadResult.ResultStatus.SUCCESS)

        assertNotNull(userInfoDownloadResult.user)
        assertEquals(UserInfoDownloadResult.ResultStatus.SUCCESS, userInfoDownloadResult.status)
        assertEquals("Stefan", userInfoDownloadResult.user?.getUserName())
    }
}