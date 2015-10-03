
module SoManyFeeds {
    export module Article {
        export interface Entity {
            id: number,
            source: string,
            title: string,
            link: string,
            content: string,
            date: string,
        }

        export interface ViewModel {
            title: string,
            link: string,
            content: string,
            date: string,
        }

        export class Presenter {
            private sanitize: (string) => string;

            constructor(sanitize: (string) => string) {
                this.sanitize = sanitize;
            }

            present(article: Entity): ViewModel {
                return {
                    title: article.title,
                    link: article.link,
                    content: this.sanitize(article.content),
                    date: article.date,
                }
            }
        }
    }
}
