///<reference path="angular/angular.d.ts"/>
///<reference path="feed.ts"/>
///<reference path="article.ts"/>

module SoManyFeeds {
    angular
        .module("SoManyFeedsApp", ["ngResource", "ngSanitize"])

        .value("availableFeeds", [
            {name: "Github", slug: "github", selected: false},
            {name: "Google+", slug: "gplus", selected: true},
            {name: "Pivotal Blog", slug: "pivotal", selected: true},
        ])

        .service("feedPresenter", () => new Feed.Presenter())
        .service("articlePresenter", ["$sanitize", ($san) => new Article.Presenter($san)])
        .factory("articlesResource", ["$resource", ($res) => $res("/articles")])

        .controller("articlesController", ["$scope", "availableFeeds", "feedPresenter", "articlePresenter", "articlesResource",
            function ($scope,
                      availableFeeds: Array<Feed.Entity>,
                      feedPresenter: Feed.Presenter,
                      articlePresenter: Article.Presenter,
                      articlesResource) {

                let presentFeed = (feed) => feedPresenter.present(feed, availableFeeds);
                let presentArticle = (article) => articlePresenter.present(article);

                $scope.feeds = availableFeeds.map(presentFeed);

                articlesResource.get()
                    .$promise.then(function (response) {
                        let entities: Array<Article.Entity> = response.articles;
                        $scope.articles = entities.map(presentArticle)
                    })
            }
        ]);
}
