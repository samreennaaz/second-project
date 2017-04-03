'use strict';
var app = angular.module('app', ['ngRoute','ngResource','ngCookies']);
 
    app.config(function($routeProvider) {
        $routeProvider
        
            .when('/', {
                controller: 'HomeController',
                templateUrl: 'c_home/home.html'
            })
 
            .when('/login', {
                controller: 'UserController',
                templateUrl: 'c_user/login.html'
            })
            
            .when('/chatforum', {
                controller: 'ChatForumController',
                templateUrl: 'c_chatforum/chatforum.html'
            })
            
             .when('/logout', {
                controller: 'UserController'	
            })
            
            .when('/blogpage', {
                controller: 'BlogController',
                templateUrl: 'c_blog/blog.html'
            })
            
            .when('/bloglist', {
                controller: 'BlogController',
                templateUrl: 'c_blog/bloglist.html'
            })
              
            .when('/userlist', {
                controller: 'UserController',
                templateUrl: 'c_user/userlist.html'
            })
            
            .when('/myProfile', {
                controller: 'UserController',
                templateUrl: 'c_user/profile.html'
            })
            
            .when('/eventpage', {
                controller: 'EventController',
                templateUrl: 'c_event/event.html'
            })
            
            .when('/friend', {
                controller: 'FriendController',
                templateUrl: 'c_friend/viewfriend.html'
            })
            
            .when('/friendrequest', {
                controller: 'FriendController',
                templateUrl: 'c_friend/friendrequest.html'
            })
            
            .when('/aboutus', {
                templateUrl: 'c_about/aboutus.html'
            })
            
            .when('/chat', {
                controller: 'ChatController',
                templateUrl: 'c_chat/chat.html'
            })
            
            .when('/blogview', {
                controller: 'BlogController',
                templateUrl: 'c_blog/viewblog.html'
            })
 
           .otherwise({ redirectTo: '/' });
    });
   
app.run( function ($rootScope, $location,$cookieStore, $http){ 
	
	$rootScope.$on('$locationChangeStart', function (event, next, current) {
        // redirect to login page if not logged in and trying to access a restricted page
        var restrictedPage = $.inArray($location.path(), ['/login','/bloglist','/aboutus']) === -1;
        console.log("restrictedPage:" +restrictedPage)
        var loggedIn = $rootScope.currentUser.userId;
        console.log("loggedIn:" +loggedIn)
        if(!loggedIn)
        	{
        		if (restrictedPage) {
        			console.log("Navigating to login page")
        			$location.path('/login');
        		}
        	}
        else
        	{
        		var role= $rootScope.currentUser.role;
        		var userRestrictedPage=  $.inArray($location.path(), ['/userlist']) == 0;
        		
        		if(userRestrictedPage && role!='admin')
        			{
        				alert("You can not do this operation as you are logged as :" + role);
        				$location.path('/');
        			}
        	}
        
    });
	
    // keep user logged in after page refresh
    $rootScope.currentUser = $cookieStore.get('currentUser') || {};
    if ($rootScope.currentUser) {
        $http.defaults.headers.common['Authorization'] = 'Basic ' + $rootScope.currentUser; 
    }
});
