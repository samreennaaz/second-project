'use strict';

app.factory('User', ['$resource', function ($resource) {
    return $resource('http://localhost:8085/test3/user/:userId', {userId: '@userId'},
	{
		updateUser: {method: 'PUT'}
	}
    );
}]);
app.controller('UserController', ['$http','$scope','$cookieStore','User','UserService','$location','$rootScope',function($http,$scope,$cookieStore,User, UserService,$location, $rootScope) {
    var ob = this;
    ob.users=[];
    ob.user = new User(); 
    ob.fetchAllUsers = function(){
        /*ob.users = User.query();*/
    	UserService.fetchAllUsers().then(function(d){
   				ob.users = d;
   			},function(errResponse){
   				console.error('Error while fetching users');
    	});
    };
    ob.getSelectedUser = myProfile
    
    function myProfile(){
        console.log("MyProfile...")
    	UserService.myProfile().then(function(d){
   				ob.user = d;
   				$location.path("/myProfile");
   			},function(errResponse){
   				console.error('Error while fetch profile');
    	});
    };
    ob.fetchAllUsers();
    ob.addUser = function(){
	console.log('Inside save');
	if($scope.userForm.$valid) {
	  ob.user.$save(function(user){
	     console.log(user); 
	     ob.flag= 'created';	
	     ob.reset();	
	     ob.fetchAllUsers();
	  },
	  function(err){
	     console.log(err.status);
	     ob.flag='failed';
	  }
          );
        }
    }; 
    ob.editUser = function(userId){
	    console.log('Inside edit');
            ob.user = User.get({ userId: userId}, function() {
	       ob.flag = 'edit'; 
	    });
    };    
    ob.updateUserDetail = function(){
	console.log('Inside update');
	if($scope.userForm.$valid) {
    	   ob.user.$updateUser(function(user){
    		console.log(user); 
		ob.updatedId = user.userId;
		ob.reset();
		ob.flag = 'updated'; 
    		ob.fetchAllUsers();
           });
	}
    };	
    ob.deleteUser = function(userId){
	    console.log('Inside delete');
	    ob.user = User.delete({ userId: userId}, function() {
		ob.reset();  
		ob.flag = 'deleted';
    		ob.fetchAllUsers(); 
	    });
    };		
    ob.reset = function(){
    	ob.user = new User();
        $scope.userForm.$setPristine();
    };	
    ob.cancelUpdate = function(userId){
	    ob.user = new User();
	    ob.flag= '';	
   	    ob.fetchAllUsers();
    }; 
    ob.authenticate = function(user){
    	console.log("authenticate...")
    	UserService
    		.authenticate(user)
    		.then(
    				function(d){
    					
    					ob.user = d;
    					console.log("user.errorcode:" + ob.user.errorCode)
    					if(ob.user.errorCode == "404")
    						{
    							alert("Invalid credentials. Please try again.")
    							ob.user.username = "";
    							ob.user.password = "";
    						}else{
    							console.log("Valid credentials. Navigating to home page")
    							$rootScope.currentUser = {
    								userId : ob.user.userId,
    								username : ob.user.username,
    								role : ob.user.role
    							};
    							$http.defaults.headers.common['Authorization'] = 'Basic'
    								+ $rootScope.currentUser;
    							$cookieStore
    								.put(
    										'currentUser',
    										$rootScope.currentUser);
    							$location.path('/');
    						}	
    				},
    				function(errorResponse){
    					console.error('Error while authenticate Users');
    				});
    };
    ob.logout = function(){
    	console.log('logout..');
    	$rootScope.currentUser = {};
    	$cookieStore.remove('currentUser');
    	
    	console.log('calling the method logout of user service');
    	UserService.logout()
    	$location.path('/login');	
    };
    ob.login = function(){
    	{
    		console.log('login validation???', ob.user);
    		ob.authenticate(ob.user);
    	}
    };
}]);     