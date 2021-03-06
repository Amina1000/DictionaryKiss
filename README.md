# DictionaryKiss app

## Урок 1. Архитектура приложения:
1. Создайте свой вариант Словаря с использованием MVP. Используйте RxJava и Retrofit. Детали внешнего вида и UI/UX остаются на ваше усмотрение.
## Урок 2. Архитектура MVVM:
1. Используйте в своем проекте ViewModel, LiveData и Dagger. Детали внешнего вида и UI/UX остаются на ваше усмотрение.
## Урок 3. Koin и корутины
1. Используйте в своем проекте Koin и Coroutines.
## Урок 4. Picasso/Glide/Coil, Room
1. Имплементируйте Room в качестве БД и создайте отдельный экран для истории.
2. Создайте экран с описанием слова. Используйте Picasso или Glide для загрузки изображения.
3. Добавьте на главный экран поиск слова в истории запросов: по тапу на иконку меню должно открываться диалоговое окно, в котором можно ввести слово и, если оно есть в базе, открыть экран с описанием слова.
4. * Добавьте функционал для списка «любимых» слов. Это значит, что пользователь может пометить любое слово, чтобы поместить его в список Favorites, который открывается в отдельном окне. Список можно редактировать — например, удалять из него слова.
5. * Доработайте экран с описанием слова: получайте с сервера больше информации о слове (помимо вариантов перевода) и отображайте более полную информацию (транскрипция, примеры использования и т. д.).
## Урок 5. Kotlin DSL и Многомодульность
1. Используйте Kotlin DSL.
2. Переведите свой проект на многомодульную структуру.
## Урок 6. Углубляемся в асинхронную работу
1. Добавьте в свой проект динамическую фичу
2. Внедрите возможность обновления приложения
3. * Опубликуйте приложение в магазине.
## Урок 7. Scopes, Delegates, refactoring
1. Добавьте скоупы в свои зависимости.
2. Добавьте делегат viewById в свой проект.
3. Отрефакторите приложение.
4. * Добавьте snackBar в качестве уведомления об отсутствии связи с сетью и прячьте его, когда сеть появляется.
5. * Изучите другие имплементации Delegate: Map, Vetoable и NotNull.
6. * Напишите класс OnlineRepository вместо класса OnlineLiveData, как это описано в методичке.
## Урок 8. Android 12
1. Подготовьте своё приложение к Android 12 с учётом всех новых фич и ограничений операционной системы (обратите пристальное внимание на изменения в Android 10 — материалы прилагаются к уроку);
2. Добавьте новый Splash Screen к своему приложению с помощью Splash Screen API;
