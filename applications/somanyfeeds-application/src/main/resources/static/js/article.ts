
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
            private dateOptions = {weekday: "short", year: "numeric", month: "short", day: "numeric", hour12: false};

            constructor(sanitize: (string) => string) {
                this.sanitize = sanitize;
            }

            present(article: Entity): ViewModel {
                let date = new Date(Date.parse(article.date));
                let presentedDate = date.toLocaleString("en-US", this.dateOptions);

                return {
                    title: article.title,
                    link: article.link,
                    content: this.sanitize(article.content),
                    date: presentedDate,
                }
            }
        }
    }
}
