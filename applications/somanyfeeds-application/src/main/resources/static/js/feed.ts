
module SoManyFeeds {
    export module Feed {
        export interface Entity {
            name: string,
            slug: string,
            selected: boolean
        }

        export interface ViewModel {
            name: string,
            path: string,
            selectedClass: string
        }

        export class Presenter {
            present(feed: Entity, availableFeeds: Array<Entity>): ViewModel {
                var path: String = "/";
                var selectedSlugs = availableFeeds
                    .filter(feed => feed.selected)
                    .map(feed => feed.slug);

                if (feed.selected) {
                    path += selectedSlugs
                        .filter(slug => slug != feed.slug)
                        .sort()
                        .join(",")
                } else {
                    path += selectedSlugs
                        .concat(feed.slug)
                        .sort()
                        .join(",")
                }

                return {
                    name: feed.name,
                    path: path,
                    selectedClass: feed.selected ? "selected" : "not-selected"
                }
            }
        }
    }
}
