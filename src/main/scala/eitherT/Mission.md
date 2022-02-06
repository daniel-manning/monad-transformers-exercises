Welcome back.

We've ended up having to write a lot of awaits or to nest a lot of steps to extract our values. Await isn't a good idea
in production code because it uneccessarily blocks the executing thread. Some more discussion [here](https://groups.google.com/g/akka-user/c/26Y1RbscFGA/m/-Vry5QQNHaIJ)

Now try using your EitherT knowledge to rewrite it. You can compare your version against one written by our developers [here](https://github.com/daniel-manning/monad-transformers-exercises/tree/eitherT)