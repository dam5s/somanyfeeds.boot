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
        .module("SoManyFeedsApp", ["ngResource", "ngSanitize"])

        .value("availableFeeds", [
            {name: "Github", slug: "github", selected: false},
            {name: "Google+", slug: "gplus", selected: true},
            {name: "Pivotal Blog", slug: "pivotal", selected: true},
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
                articlesService.getByFeeds(selectedFeeds()).then(function (response) {
                    let entities: Array<Article.Entity> = response.data.articles;
                    $scope.articles = entities.map(presentArticle)
                });
                $scope.feeds = availableFeeds.map(presentFeed);
            };

            $scope.$on("$locationChangeSuccess", () => {
                let slugs = $location.path().replace(/^\//, "").split(",");

                availableFeeds.forEach((feed: Feed.Entity) => {
                    feed.selected = slugs.some((s) => s == feed.slug);
                });
                refreshFeeds();
            });
        });
}
