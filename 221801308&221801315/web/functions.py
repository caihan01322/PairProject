from app import cursor


def combine_keywords_and_articles(articles):
    """给每篇论文找到其关键词

    关键词和论文存储分为两个表，该函数负责给每篇文章找到对应的关键词组

    Args:
        articles: 没有关键词组的论文组

    Returns:
        data: 加上关键词组后的论文组信息
    """
    data = []
    for article in articles:
        # 提取每篇论文的关键词组
        keywords = []
        cursor.execute(
            "select keyword from keywords where title=%s", article[1])
        keyword_list = cursor.fetchall()
        if keyword_list is not None:
            for keyword in keyword_list:
                keywords.append(keyword[0])

        content = {"meeting": article[0], "title": article[1], "year": article[2],
                   "keywords": keywords, "abstract": article[3],
                   "doiLink": article[4]}
        data.append(content)
    return data