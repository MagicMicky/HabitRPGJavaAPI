HabitRPGJavaAPI
===============

The API for HabitRPG to use in other JAVA/Android Project...


#How it works#
To use HabitRPGJavaAPI, you first need to import the library to your project. The project is pretty easy to use: you have different class that represents either the items or the user,
and you have the classes that allows you to interact with the API. For example now, if you want to retrieve an user an its associated tasks.

```java
//Create a new config
HostConfig config = new HostConfig("https://habitrpg.com/","80","myAPIkey","myUSERkey");
//Create a callback
	OnHabitsAPIResult callback = new OnHabitsAPIResult() {
		Handler mainHandler;
		private int nbRequests=0;
		@Override
		public void onUserReceived(final User us) {
			//Called when we receive some news about the user....
			this.user=us;
		}

		@Override
		public void onPostResult(final double xp,final double hp,final double gold,final double lvl,
				final double delta) {
				//this is called when you use a POST method (right now)
		}

		@Override
		public void onError(final String message) {
		//Display message from error
		}

		@Override
		public void onPreResult() {
			//Show progress or whatever
		}
	};
	
//Create a GetUser, by giving him the callback and the config
GetUser getUser = new GetUser(callback,config);
callback.onPreResult();

//Retrieve the answer from the API
Answer as = getUser.getData();

//If there is an answer, parse the information. It will call the callback automatically...
if(as!=null)
	as.parse();
```