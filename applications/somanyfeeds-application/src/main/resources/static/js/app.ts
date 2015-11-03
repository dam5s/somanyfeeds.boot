///<reference path="angular/angular.d.ts"/>
///<reference path="feed.ts"/>
///<reference path="article.ts"/>

module SoManyFeeds {
    interface ArticlesListResponse {
        articles: Array<Article.Entity>
    }

    interface ArticlesService {
        getByFeeds(feeds: Array<Feed.Entity>): angular.IHttpPromise<ArticlesListResponse>
    }

    angular
        .module("SoManyFeedsApp", ["ngSanitize"])

        .value("availableFeeds", [
            {name: "About", slug: "about", selected: false},
            {name: "Github", slug: "github", selected: false},
            {name: "Google+", slug: "gplus", selected: false},
            {name: "Pivotal Blog", slug: "pivotal", selected: false}
        ])

        .service("feedPresenter", () => new Feed.Presenter())
        .service("articlePresenter", ($sanitize) => new Article.Presenter($sanitize))
        .factory("articlesService", ($http: angular.IHttpService) => {
            return {
                getByFeeds: (feeds: Array<Feed.Entity>) => {
                    let slugs = feeds
                        .map(f => f.slug)
                        .sort()
                        .join(",");

                    return $http.get(`/articles/${slugs}`)
                }
            }
        })

        .controller("articlesController", function ($scope,
                                                    $location: angular.ILocationService,
                                                    availableFeeds: Array<Feed.Entity>,
                                                    feedPresenter: Feed.Presenter,
                                                    articlePresenter: Article.Presenter,
                                                    articlesService: ArticlesService) {

            let presentFeed = (feed) => feedPresenter.present(feed, availableFeeds);
            let presentArticle = (article) => articlePresenter.present(article);
            let selectedFeeds = () => availableFeeds.filter(f => f.selected);

            let refreshFeeds = () => {
                let feeds = selectedFeeds();
                let feedsWithoutAbout = feeds.filter(f => f.slug != "about");

                $scope.aboutSelected = feeds.some(f => f.slug == "about");

                if (feedsWithoutAbout.length > 0) {
                    articlesService
                        .getByFeeds(feedsWithoutAbout)
                        .then((response) => {
                            $scope.articles = response
                                .data
                                .articles
                                .map(presentArticle);
                        });
                } else {
                    $scope.articles = [];
                }

                $scope.feeds = availableFeeds.map(presentFeed);
            };

            $scope.$on("$locationChangeSuccess", () => {
                let slugs = $location.path()
                    .replace(/^\//, "")
                    .split(",")
                    .filter(s => s.length > 0);

                availableFeeds.forEach((feed: Feed.Entity) => {
                    feed.selected = slugs.some((s) => s == feed.slug);
                });
                refreshFeeds();
            });

            if ($location.path() == "") $location.path("/about,gplus,pivotal")
        });
}
