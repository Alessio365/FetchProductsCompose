package eu.tutorial.fetchproductscompose.viewmodel

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import eu.tutorial.fetchproductscompose.data.Product
import eu.tutorial.fetchproductscompose.network.RetrofitInstance
import kotlinx.coroutines.launch

class ProductViewModel : ViewModel() {
    var products = mutableStateListOf<Product>()
    var isLoading = mutableStateOf(false)

    fun fetchProducts() {
        isLoading.value = true
        viewModelScope.launch {
            try {
                val fetchedProducts = RetrofitInstance.api.getProducts()
                products.clear()
                products.addAll(fetchedProducts)
            } catch (e: Exception) {

            } finally {
                isLoading.value = false
            }
        }
    }
}