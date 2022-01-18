package com.geekbrains.tests.repository

import com.geekbrains.tests.presenter.RepositoryContract

interface IRepositoryProvider {
    fun getRepository(): RepositoryContract
}