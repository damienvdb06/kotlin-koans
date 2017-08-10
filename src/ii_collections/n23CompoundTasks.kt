package ii_collections

fun Shop.getCustomersWhoOrderedProduct(product: Product): Set<Customer> =
        customers.filter { product in it.orders.flatMap { it.products } }.toSet()

fun Customer.getMostExpensiveDeliveredProduct(): Product? =
        this.orders.filter { it.isDelivered }.flatMap { it.products }.maxBy { it.price }

fun Shop.getNumberOfTimesProductWasOrdered(product: Product): Int =
    this.customers.flatMap { it.orders.flatMap { it.products } }.count { it.equals(product) }
