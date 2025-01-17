package com.example.polidea.view.main.search.presenter

import androidx.paging.DataSource
import com.example.polidea.domain.dto.QuestionDto
import com.example.polidea.domain.usecase.SearchUseCase
import io.reactivex.disposables.CompositeDisposable

class SearchDataSourceFactory(
	private val searchUseCase: SearchUseCase,
	private val compositeDisposable: CompositeDisposable,
	private val onErrorListener: () -> Unit
) : DataSource.Factory<Int, QuestionDto>() {

	private var query: String = ""
	private var order: String = ""
	private var sort: String = ""

	private var dataSource: SearchDataSource? = null

	override fun create(): DataSource<Int, QuestionDto> {
		dataSource = SearchDataSource(searchUseCase, compositeDisposable, query, order, sort, onErrorListener)
		return dataSource!!
	}

	fun search(query: String, order: String, sort: String) {
		this.query = query
		this.order = order
		this.sort = sort
		dataSource?.invalidate()
	}
}