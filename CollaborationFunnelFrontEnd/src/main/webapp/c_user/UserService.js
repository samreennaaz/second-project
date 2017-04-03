'use strict';

	app.factory('UserService',['$http','$q','$rootScope', function($http, $q, $rootScope){
		console.log("UserService..")
		
		var BASE_URL = 'http://localhost:8085/test3'
			return{
			
			fetchAllUsers: function(){
				return $http.get(BASE_URL+'/user')
				.then(
					function(response){
						return response.data;
					},
					null
				);
			}, 
			
			myProfile: function(){
				return $http.get(BASE_URL+'/myProfile')
				.then(
					function(response){
						$rootScope.selectedUser = response.data;
						return response.data;
					},
					null
				);
			},
			
				logout: function(){
					console.log('logout....')
					return $http.get(BASE_URL+'/user/logout')
					.then(
						function(response){
							return response.data;
						},
						null
					);
				},
				authenticate: function(user){
					console.log("Callig the method authenticate with the user :"+user)
					return $http.post(BASE_URL+'/user/authenticate/',user)
					.then(
						function(response){
							return response.data;
						},
						null
					);
				}
		};
	}]);