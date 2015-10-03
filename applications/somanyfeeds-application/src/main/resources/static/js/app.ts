///<reference path="angular/angular.d.ts"/>
///<reference path="feed.ts"/>

module SoManyFeeds {
    angular
        .module("SoManyFeedsApp", [])

        .value("availableFeeds", [
            {name: "Github", slug: "github", selected: false},
            {name: "Google+", slug: "gplus", selected: true},
            {name: "Pivotal Blog", slug: "pivotal", selected: true},
        ])
        .service("feedPresenter", function () {
            return new Feed.Presenter()
        })
        .controller("articlesController", function ($scope,
                                                    availableFeeds: Array<Feed.Entity>,
                                                    feedPresenter: Feed.Presenter) {

            var presentFeed = function (feed) {
                return feedPresenter.present(feed, availableFeeds);
            };

            $scope.feeds = availableFeeds.map(presentFeed);
        });
}
