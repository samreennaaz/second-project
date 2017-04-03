'use strict';

	app.factory('BlogService',['$http','$q','$rootScope', function($http, $q, $rootScope){
		console.log("BlogService..")
		
		var BASE_URL = 'http://localhost:8085/test3'
			return{
			getBlog: function(blogId){
				return $http.get(BASE_URL+'/blog/'+blogId)
				.then(
					function(response){
						$rootScope.selectedBlog = response.data;
						return response.data;
					},
					null
				);
			}
		};
	}]);