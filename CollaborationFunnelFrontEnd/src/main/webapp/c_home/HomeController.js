'use strict';

app.controller('HomeController', ['$scope','UserService','$rootScope',function($scope,UserService,$rootScope) {
	
	console.log("HomeController....");
	
	var ob = this;
	
	ob.getCurrentUser = function()
	{
		console.log("Loading current user")
		console.log("Current user:" + $rootScope.currentUser)
		if(!$rootScope.currentUser)
			{
				console.log("User not logged in")
				$rootScope.currentUser="";
			}
		console.log("User is logged in")
		return $rootScope.currentUser;
	}
	
	ob.getCurrentUser();
}]);