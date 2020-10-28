# Bags, Queues, and Stacks

Several fundamental data types involve collections of objects.

**Def:** Collection of object = set of values + operations (adding, removing, examining)



<img src="file:///var/folders/t3/prdfgv6d0_50z2kj_x39p9500000gn/T/TemporaryItems/(A%20Document%20Being%20Saved%20By%20screencaptureui)/Screen%20Shot%202020-10-18%20at%2019.25.12.png" title="" alt="Screen Shot 2020-10-18 at 19.25.12.png" data-align="center">

* *Generics*: an essential chracteristic of collection ADTs is that it should be used for any type of data. *Generics* mechanism enables this capability. The notation **<Item>** after the class name defines the **Item** as a *type parameter*, a symbolic placeholder for some concrete type to be used by the client. 

* *Autoboxing*: Type parameters have to be instantiated as reference types. Java automatically converts between a primitive type and its corresponding wrapper type in assignements, method arguments and arithmetic/logic expressions, which enables us to use generics with primitive types. 

<img title="generics for primitive type" src="file:///var/folders/t3/prdfgv6d0_50z2kj_x39p9500000gn/T/TemporaryItems/(A%20Document%20Being%20Saved%20By%20screencaptureui%202)/Screen%20Shot%202020-10-18%20at%2019.33.03.png" alt="Screen Shot 2020-10-18 at 19.33.03.png" data-align="center">

* *Iterable collections*. For many applications, the client's requirement is just to process each of the items in some way, or to *iterate* through the items in the collection.           

* *Bags*: is a collection where removing items is not supported. It only collects items.

* *FIFO queues* is a collection based on the *first-in-first-out* policy.

* *Pushdown stack* is a collection based on the *last-in-first-out* policy.
