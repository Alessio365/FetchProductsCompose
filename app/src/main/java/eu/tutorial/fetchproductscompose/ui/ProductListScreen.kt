package eu.tutorial.fetchproductscompose.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import eu.tutorial.fetchproductscompose.viewmodel.ProductViewModel
import eu.tutorial.fetchproductscompose.data.Product

@Composable
fun ProductListScreen(viewModel: ProductViewModel) {
    if (viewModel.isLoading.value) {
        CircularProgressIndicator(modifier = Modifier.fillMaxSize())
    } else {
        LazyColumn {
            items(viewModel.products) { product ->
                ProductItem(product)
            }
        }
    }
}

@Composable
fun ProductItem(product: Product) {
    Row(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
    ) {
        val painter = rememberImagePainter(product.image)
        Image(
            painter = painter,
            contentDescription = product.title,
            modifier = Modifier.size(100.dp)
        )
        Spacer(modifier = Modifier.width(8.dp))
        Column(
            modifier = Modifier
                .weight(1f)
                .padding(8.dp)
        ) {
            Text(
                text = product.title,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )

            Text(
                text = "\$${product.price}",
                style = MaterialTheme.typography.bodyMedium
            )

            Text(
                text = product.description,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis)
        }
    }
}