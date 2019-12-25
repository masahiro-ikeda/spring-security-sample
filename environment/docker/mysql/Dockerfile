# 使うDockerイメージ
FROM mysql:5.7

# MySQL設定ファイルをイメージ内にコピー
ADD ./my.cnf /etc/mysql/conf.d/my.cnf

# テーブル初期化SQLをコピー
ADD ./init/create.sql   /docker-entrypoint-initdb.d/create.sql
ADD ./init/testdata.sql /docker-entrypoint-initdb.d/create.sql

# docker run時に実行されるコマンド
CMD ["mysqld"]