package com.berniesanders.connect.data;

import static com.berniesanders.connect.util.StringUtil.nullAsEmpty;

public class NewsArticleGson {
    // required
    public String article_type;
    public String body;
    public String lang;
    public String object_type;
    public String site;
    public int status;
    public String timestamp_creation;
    public String timestamp_publish;
    public String title;
    public String url;
    public String uuid;

    // optional
    public String body_html;
    public String body_html_nostyle;
    public String body_markdown;
    public String excerpt;
    public String excerpt_html;
    public String image_url;
    public String description;
    public String description_html;
    public String news_category;
    public String news_id;
    public String news_type;

    public NewsArticle toValue() {
        return NewsArticle.builder()
                .articleType(article_type)
                .body(body)
                .lang(lang)
                .objectType(object_type)
                .site(site)
                .status(status)
                .timestampCreation(timestamp_creation)
                .timestampPublish(timestamp_publish)
                .title(title)
                .url(url)
                .uuid(uuid)
                .bodyHtml(nullAsEmpty(body_html))
                .bodyHtmlNostyle(nullAsEmpty(body_html_nostyle))
                .bodyMarkdown(nullAsEmpty(body_markdown))
                .excerpt(nullAsEmpty(excerpt))
                .excerptHtml(nullAsEmpty(excerpt_html))
                .imageUrl(nullAsEmpty(image_url))
                .description(nullAsEmpty(description))
                .descriptionHtml(nullAsEmpty(description_html))
                .newsCategory(nullAsEmpty(news_category))
                .newsId(nullAsEmpty(news_id))
                .newsType(nullAsEmpty(news_type))
                .build();
    }

    public static NewsArticleGson fromValue(final NewsArticle actionAlert) {
        final NewsArticleGson gson = new NewsArticleGson();

        gson.article_type = actionAlert.articleType();
        gson.body = actionAlert.body();
        gson.lang = actionAlert.lang();
        gson.object_type = actionAlert.objectType();
        gson.site = actionAlert.site();
        gson.status = actionAlert.status();
        gson.timestamp_creation = actionAlert.timestampCreation();
        gson.timestamp_publish = actionAlert.timestampPublish();
        gson.title = actionAlert.title();
        gson.url = actionAlert.url();
        gson.uuid = actionAlert.uuid();
        gson.body_html = actionAlert.getBodyHtml().orElse("");
        gson.body_html_nostyle = actionAlert.getBodyHtmlNostyle().orElse("");
        gson.body_markdown = actionAlert.getBodyMarkdown().orElse("");
        gson.excerpt = actionAlert.getExcerpt().orElse("");
        gson.excerpt_html = actionAlert.getExcerptHtml().orElse("");
        gson.image_url = actionAlert.getImageUrl().orElse("");
        gson.description = actionAlert.getDescription().orElse("");
        gson.description_html = actionAlert.getDescriptionHtml().orElse("");
        gson.news_category = actionAlert.getNewsCategory().orElse("");
        gson.news_id = actionAlert.getNewsId().orElse("");
        gson.news_type = actionAlert.getNewsType().orElse("");

        return gson;
    }
}
