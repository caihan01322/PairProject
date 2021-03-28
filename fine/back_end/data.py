from selenium import webdriver
import time
import pymysql


# 模拟点击
def pointer(num):
    pointer_url = "https://ieeexplore.ieee.org/document/67511" + str(num)
    br.get(pointer_url)
    time.sleep(2)
    get_message(num)
    # ul = br.find_elements_by_class_name("publ-list")[1]
    # lis = ul.find_elements_by_xpath('li')
    # for i in range(len(lis)):


# 获取数据
def get_message(num):
    if len(br.find_elements_by_xpath('//div[@class="u-mb-1"]/div')):
        abstract = br.find_elements_by_xpath(
            '//div[@class="u-mb-1"]/div')[0].text
    else:
        return

    if len(
            br.find_elements_by_xpath(
                '//div[@class="u-pb-1 doc-abstract-dateadded"]')):
        date = br.find_elements_by_xpath(
            '//div[@class="u-pb-1 doc-abstract-dateadded"]')[0].text
        date = date.split(r': ')[1]
    else:
        return

    link = br.find_elements_by_xpath(
        '//div[@class="u-pb-1 stats-document-abstract-doi"]/a')
    if len(link):
        link = link[0]
        link = link.get_attribute('href')

    if len(br.find_elements_by_xpath('//h1[@class="document-title"]')):
        title = br.find_elements_by_xpath(
            '//h1[@class="document-title"]')[0].text
    else:
        return

    if len(br.find_elements_by_xpath('//div[@class="u-pb-1"]')) >= 4:
        isbn = br.find_elements_by_xpath('//div[@class="u-pb-1"]')[3].text
        isbn = isbn.split(r': ')[1]
    elif len(br.find_elements_by_xpath('//div[@class="u-pb-1"]')) >= 3:
        isbn = br.find_elements_by_xpath('//div[@class="u-pb-1"]')[2].text
        isbn = isbn.split(r': ')[1]
    else:
        return

    pointer_url = "https://ieeexplore.ieee.org/document/67511" + str(
        num) + "/keywords#keywords"
    br.get(pointer_url)
    time.sleep(2)
    key_list = br.find_elements_by_xpath(
        '//ul[@class="u-mt-1 u-p-0 List--no-style List--inline"]/li')

    key_word = ""
    for k in key_list:
        key_word += k.text

    key_list = key_word.split("\n,")
    key_word = key_word.split("\n")
    key_word = "".join(key_word)

    # print("\nabstract: " + abstract)
    # print("\ndate: " + date)
    # print("\nlink: " + link)
    # print("\ntitle: " + title)
    # print("\nisbn: " + isbn)
    # print("\nkey_word: " + key_word)
    save_data(isbn, key_word, title, link, date, abstract)
    save_tag(key_list)


# 获取tag
def get_tag(num):
    pointer_url = "https://ieeexplore.ieee.org/document/66188" + str(
        num) + "/keywords#keywords"
    br.get(pointer_url)
    time.sleep(2)
    key_list = br.find_elements_by_xpath(
        '//ul[@class="u-mt-1 u-p-0 List--no-style List--inline"]/li')

    key_word = ""
    for k in key_list:
        key_word += k.text

    key_word = key_word.split("\n,")
    # for k in key_word:
    #     print(k)

    save_tag(key_word)


def save_data(isbn, key_word, title, link, date, abstract):
    db = pymysql.connect(host='47.98.191.214',
                         user='root',
                         password='Chuangye6+1+2',
                         port=3306,
                         db='whichpage')
    con = db.cursor()

    # 将数据写入MySQL
    d = (isbn, title, key_word, date, link, abstract)
    sql = 'INSERT INTO page_list(isbn, title, tag, year, link, abstract) values(%s, %s, %s, %s, %s, %s)'
    try:
        con.execute(sql, d)
        db.commit()
        print("succeed to save!")
    except Exception as ex:
        db.rollback()
        print("have something wrong, and %s" % ex)

    con.close()
    db.close()


# 将tag导入数据库
def save_tag(key_word):
    db = pymysql.connect(host='47.98.191.214',
                         user='root',
                         password='Chuangye6+1+2',
                         port=3306,
                         db='whichpage')
    con = db.cursor()

    belong = "CVPR"
    year = "2013"

    for k in key_word:
        key = (k, belong, year)
        sql = 'select * from tag_list where name = %s and belong = %s and year = %s'
        try:
            con.execute(sql, key)
            if con.fetchone() is not None:
                sql = 'update tag_list set num = num + 1 where name = %s and belong = %s and year = %s'
                con.execute(sql, key)
                db.commit()
                print("succeed to +1!")
            else:
                # 将数据写入MySQL
                d = (k, belong, year)
                sql = 'INSERT INTO tag_list(name, belong, year) values(%s, %s, %s)'
                try:
                    con.execute(sql, d)
                    db.commit()
                    print("succeed to save!")
                except Exception as ex:
                    db.rollback()
                    print("have something wrong, and %s" % ex)
        except Exception as ex:
            db.rollback()
            print("have something wrong, and %s" % ex)

    con.close()
    db.close()


# 主程序
if __name__ == '__main__':
    br = webdriver.Chrome()
    for i in range(10, 60):
        # pointer(i)
        get_tag(i)
