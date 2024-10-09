package com.example.quotesapp.data.repository

import android.util.Log
import com.example.quotesapp.data.local.QuoteDatabase
import com.example.quotesapp.data.mappers.toQuote
import com.example.quotesapp.data.remote.QuoteApi
import com.example.quotesapp.domain.model.Quote
import com.example.quotesapp.domain.model.QuoteHome
import com.example.quotesapp.domain.repository.QuoteRepository
import com.example.quotesapp.util.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext

class QuoteRepositoryImplementation(private val api:QuoteApi, private val db:QuoteDatabase):QuoteRepository {


    override fun getQuote(): Flow<Resource<QuoteHome>> {

        return flow {

            var quoteHome:QuoteHome? = null

            emit(Resource.Loading())

            Log.d("TAG",Thread.currentThread().name)



            try {

                withContext(Dispatchers.IO){

                    val quotesListDef = async { api.getQuotesList().map { it.toQuote() } }
                    val qotDef =  async { api.getQuoteOfTheDay().map { it.toQuote() } }

                    val quotesList = quotesListDef.await()
                    val qot=qotDef.await()

//                     quoteHome = QuoteHome(
//                        quotesList = quotesList.toMutableList(),
//                        quotesOfTheDay = qot
//                    )

                    db.getQuoteDao().deleteAll()

                    quotesList.let { list->
                        db.getQuoteDao().insertQuoteList(quotesList)
                    }

                    Log.d("TAG","From impl inside catch-" + db.getQuoteDao().getAllQuotes().size)

                    quoteHome= QuoteHome(
                        quotesList = db.getQuoteDao().getAllQuotes(),
                        quotesOfTheDay = qot
                    )

                }

                emit(Resource.Success(quoteHome))

            }catch (e:Exception){
                emit(Resource.Error(e.message.toString()))
                Log.d("TAG","From impl inside catch "+e.message.toString())
            }

        }

    }

    override suspend fun saveLikedQuote(quote: Quote) {
        db.getQuoteDao().insertLikedQuote(quote)
    }
}