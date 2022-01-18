package com.geekbrains.tests.repository

import com.geekbrains.tests.presenter.RepositoryContract

object RepositoryProvider : IRepositoryProvider {
    override fun getRepository(): RepositoryContract = FakeGitHubRepository()
}